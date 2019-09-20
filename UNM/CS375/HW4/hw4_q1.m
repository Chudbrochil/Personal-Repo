function hw4_q1

Alphas = [0, 1.0, 10.0, 100.0, 1000.0, 10000, 100000, 1000000]; 
n = 200;
tot_it = 100;

list_of_errors = zeros(1,length(Alphas));

for alpha=1:length(Alphas)
    %Generate Linear System
    [A,b] =  generate_SPD_mat_and_rhs_vec(n, Alphas(alpha));
    
    %Compute Solution
    x_jacobi = my_jacobi(A,b,tot_it);%compute solution with your my_jacobi() function
    
    %"True" Solution
    x_t = A\b;
    
    %Errors
    err_jacobi = norm(x_t - x_jacobi) / norm(x_t); %compute norm of the error
    
    list_of_errors(alpha) = err_jacobi;
    
end

% Displaying errors in a table
T = table(Alphas',list_of_errors');
T.Properties.VariableNames = {'Alpha' 'Error'};
disp(T);


