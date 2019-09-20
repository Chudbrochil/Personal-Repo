close all
clc

ns = [50,100,200,300,400];

list_of_A = cell(1,5);
rel_errors = zeros(1,5);
residuals = zeros(1,5);
condition_nums = zeros(1,5);

for a=1:5
    n = ns(a);
    A = zeros(n,n);
    for i=1:n
        for j=1:n
            % The function to fill out our matrices.
            A(i,j) = sqrt(2*(i-j)^2 + n/5);
        end
    end
    
    % Calculating single values for our problem.
    x_exact = ones([n,1]);
    b = A * x_exact;
    x_c = A\b;
    rel_error = norm(x_c - x_exact,2) / norm(x_exact,2);
    residual = norm(b - (A * x_c), 2) / norm(b,2);
    
    % Inserting our values into lists
    list_of_A{a} = A;
    rel_errors(a) = rel_error;
    residuals(a) = residual;
    condition_nums(a) = cond(A);
    
end

% Plotting relative error and residuals
figure
error_plot = semilogy(ns, rel_errors);
hold on
residual_plot = semilogy(ns, residuals);
title({"Relative Error and ", "Residual as N increases"});
ylabel("Relative Error vs. Residual");
xlabel("N");
hold off

legend([error_plot residual_plot], ["Relative error", "Residual"],...
        'Location','Northwest');

% Plotting the condition number
figure
semilogy(ns, condition_nums);
title("Condition Number as N increases");
ylabel("Condition Number");
xlabel("N");
legend("Condition number",'Location','Northwest');




