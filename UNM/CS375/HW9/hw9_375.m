clear
clc
close all

%% Q2
t0 = 0;
T = 10;
t=linspace(t0,T,1000);
f_poss = (2/3).*t.^(3/2);
figure
hold on
plot(t,f_poss,'r-')

h=1e-4;        %step size
t=(t0:h:T);    %vector of descrete grid points
n=round((T-t0)/h); %number of sub-intervals
f=@(t, y) power(y, 1/3);   %y is a 2x1 vector y=[y1;y2]. Hence f is also a vector   
y = euler_fun(h, n, t, f);
plot(t,y)
legend('exact y1', 'numerical y1')
title('2. Exact y vs. Numerical Approx. of y using Eulers Method')
xlabel('t')
ylabel('y')

%% Q3
t0=0;
T = 1;
hvalues = logspace(-1, -3, 12);
t = t0:0.01:T;
y_exact = (1/2).*(1+exp(-t.^2));
f=@(t, y) t - 2*t*y;

for i=1:size(hvalues,2)
    t = t0:hvalues(i):T;
    n=(T-t0)/hvalues(i); %number of sub-intervals
    
    y_euler = euler_fun(hvalues(i), n, t, f);
    y_rk4 = rk4_fun(hvalues(i), n, t, f);
    error_euler(i) = abs(y_euler(end) - y_exact(end));
    error_rk4(i) = abs(y_rk4(end) - y_exact(end));

end

figure
loglog(hvalues, error_euler)
hold on
loglog(hvalues, error_rk4)
legend('euler error', 'rk4 error', 'Location', 'southeast')
title('3. RK4 error & Euler error')
xlabel('h values')
ylabel('error')

%% Q4 -- E.C.
f_q4 = @(t, y) y^2 - y^3;
t0 = 0;
h1 = 10;
h2 = 0.1;
T = 2 / 0.01;    % 2 / Delta
t1 = t0:h1:T;    %vector of descrete grid points
t2 = t0:h2:T;
n1 = round((T-t0)/h1);
n2 = round((T-t0)/h2);

y_rk4_h1 = rk4_fun_q4(h1, n1, t1, f_q4);
y_rk4_h2 = rk4_fun_q4(h2, n2, t2, f_q4);

figure
hold on
plot(t1, y_rk4_h1)
legend('h=10')
title('4. Rk4 Flame problem: h=10')
xlabel('t')
ylabel('y')

figure
hold on
plot(t2, y_rk4_h2)
legend('h=0.1')
title('4. Rk4 Flame problem: h=0.1')
xlabel('t')
ylabel('y')

%% Euler function
function y = euler_fun(h, n, t, f)   
    y=zeros(1,length(t));   %to pre-allocate a vector for the solution
    y(1) = 1;
    for i=1:n
       K=f(t(i),y(i));
       y(i+1)=y(i)+h*K;
    end
end

%% RK4 function
function y = rk4_fun(h, n, t, f)    
    y=zeros(1, length(t)); %to pre-allocate a vector for the solution
    y(1)=1;           %to set the initial solution at t=0
    for i=1:n
       K1=f(t(i),y(i));
       K2=f(t(i)+0.5*h,y(i)+0.5*h*K1);
       K3=f(t(i)+0.5*h,y(i)+0.5*h*K2);
       K4=f(t(i)+h,y(i)+h*K3);
       y(i+1)=y(i)+(1/6)*h*(K1+2*K2+2*K3+K4);
    end
end

%% RK4 function Q4 (made because different IC)
function y = rk4_fun_q4(h, n, t, f)    
    y(1)=0.01;           %set the initial solution at t=0
    for i=1:n
       K1=f(t(i),y(i));
       K2=f(t(i)+0.5*h,y(i)+0.5*h*K1);
       K3=f(t(i)+0.5*h,y(i)+0.5*h*K2);
       K4=f(t(i)+h,y(i)+h*K3);
       y(i+1)=y(i)+(1/6)*h*(K1+2*K2+2*K3+K4);
    end
end