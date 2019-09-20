% Anthony Galczak - Tristin Glunt
% CS375 HW5

clc
close all
% Q1 - Find number of zeros of f(x).

% f(x) = x^3 - y looks to have 1 real root.
% This is intuitively true by just thinking about 
% some sample y's. 
% y = 8: 8^(1/3) has 1 real root, 2. 
% y = -8: (-8)^(1/3) has 1 real root, -2.
% The other 2 roots are in the complex plane, 
% shifted by 2pi/3 and 4pi/3 in e^(i*theta).

% y is a constant, f(x) = x^3 - y
sym x;
y = 8;
f = @(x) x.^3 - y;

num_vals = 1000;
f_evals = zeros(1,num_vals);
x_s = linspace(-4,4,num_vals);

for i=1:num_vals
    f_evals(i) = f(x_s(i));
end

plot(x_s,f_evals);
title("Q1.) f(x) for y=8");
xlabel("x-axis");
ylabel("y-axis");
legend("f(x) = x^3 - y", "Location", "Northwest");

% Q3 - Test Bisection, Newton, Secant method.
fprintf("Output starts here\n");
tol = 10^-10;
format long
bi_arr = my_bisection(f, 1, 4, tol);
fprintf("Bisection root: %1.15f\n\n", bi_arr(end));

df = @(x) 3*x^2;
newt_arr = my_newton(f, df, 4, tol);
fprintf("Newton root: %1.15f\n\n", newt_arr(end));

sec_arr = my_secant(f, 1, 4, tol);
fprintf("Secant root: %1.15f\n\n", sec_arr(end));

% Q4 - Compute convergence rate for each method.
% Note: First real iteration on Newton method is 2 and on Secant
% method is 3.
bi_vals = bi_arr;
convergence_table("Bisection", bi_vals, 1);

newt_vals = newt_arr(1,2:end);
convergence_table("Newton", newt_vals, 2);

sec_vals = sec_arr(1,3:end);
convergence_table("Secant", sec_vals, 1.62);

% Function for printing tables for convergence analysis
function c = convergence_table(method_name, vals, r)

    for i=1:length(vals)
        iters(i) = i;
        errors(i) = abs(vals(i) - 2);
        if i == 1
            rates(i) = 0;
        else
            rates(i) = errors(i) / (errors(i-1) ^ r);
        end
    end
    
    fprintf("%s Method table, r = %1.2f\n", method_name, r);
    T = table(iters',errors',rates');
    T.Properties.VariableNames = {'i' 'Error' 'Conv_Rate'};
    disp(T);
    
end


% Q2 - Implement Bisection, Newton's, Secant as fn's.
function[x_arr] = my_bisection(f, a, b, tol)
    for i=1:100
        x_m = a + (b-a)/2;
        x_arr(i) = x_m;
        
        if sign(f(x_m)) == sign(f(a))
            a = x_m;
        else
            b = x_m;
        end
       
        % First halting criteria
        if (i >= 2)
            last_guess = x_arr(i-1);
            if abs(x_m - last_guess) < tol
                fprintf(['Bisection method hit halting criteria',...
                    ' 1 after %d iterations.\n'],i);
                return
            end
        end
        
        % Second halting criteria
        if abs(f(x_m)) < eps
            fprintf(['Bisection method hit halting criteria',...
                ' 2 after %d iterations.\n'],i);
            return
        end
    end
end
        
function[x_arr] = my_newton(f, df, x_0, tol)
    x_arr(1) = x_0;
    for i=2:100
        last_guess = x_arr(i-1);
        x_k = last_guess - (f(last_guess)/df(last_guess));
        x_arr(i) = x_k;
        
        if abs(x_k - last_guess) < tol
            fprintf(['Newton method hit halting criteria',...
                ' 1 after %d iterations.\n'],(i-1));
            return
        end

        if abs(f(x_k)) < eps
            fprintf(['Newton method hit halting criteria',...
                ' 2 after %d iterations.\n'],(i-1));
        end
    end
        
end

function[x_arr] = my_secant(f, x_0, x_1, tol)
    x_arr(1) = x_0;
    x_arr(2) = x_1;
    
    for i=3:100
        x_k2 = x_arr(i-2);
        x_k1 = x_arr(i-1);
        term2 = f(x_k1)*((x_k1 - x_k2)/(f(x_k1) - f(x_k2)));
        x_k = x_k1 - term2;
        x_arr(i) = x_k;
        
        if abs(x_k - x_k1) < tol
            fprintf(['Secant method hit halting criteria',...
                ' 1 after %d iterations.\n'],(i-2));
            return
        end
        
        if abs(f(x_k)) < eps
            fprintf(['Secant method hit halting criteria',...
                ' 2 after %d iterations.\n'],(i-1));
            return
        end
    end
    
end