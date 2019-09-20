function hw4_q2

tot_its = [5, 40, 80, 160, 320, 640, 1280];
num_experiments = length(tot_its);

%Generate Linear System
n = 200;
a = 200;
[A,b] =  generate_SPD_mat_and_rhs_vec(n,a);

err_jacobi = zeros(num_experiments,1);
err_cg = zeros(num_experiments,1);
exp_num = 1;

for tot_it =tot_its
    
    %Compute Solutions
    
    %Jacobi
    x_jacobi = my_jacobi(A,b,tot_it);
    
    %CG
    x_cg = my_cg(A,b, tot_it);
    
    %"True" Solution
    x_t = A\b;
    
    %Errors
    err_jacobi(exp_num) = norm(x_t - x_jacobi)/norm(x_t);
    err_cg(exp_num) = norm(x_t - x_cg)/norm(x_t);
    
    exp_num = exp_num + 1;
end

format long;

T = table(tot_its',err_jacobi,err_cg);
T.Properties.VariableNames = {'Num_Iterations' 'Relative_Error_Jacobi' 'Relative_Error_CG'};
disp(T);

