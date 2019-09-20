%Inputs:
%A: Matrix
%b: Vector
%Relative tolerance for the residual. We want (\|b-Ax\|/\|b\| < rel_tol
%Output:
%:x The solution are tot_it iterations

function x = my_cg(A, b, tot_it)

    width_of_A = size(A,2);
    x_prev = zeros(width_of_A, 1);
    r_prev = b - (A * x_prev);
    s_prev = r_prev;

    for i=1:tot_it
        alpha = (r_prev' * r_prev) / (s_prev' * A * s_prev);
        
        % Iterating all the variables
        x_iter = x_prev + (alpha * s_prev);
        r_iter = r_prev - (alpha * A * s_prev);
        s_iter = r_iter + ((r_iter' * r_iter)/(r_prev' * r_prev)) * s_prev;
        
        % Setting variables to prev for next iteration
        x_prev = x_iter;
        r_prev = r_iter;
        s_prev = s_iter;
        
    end
    
    x = x_iter;
