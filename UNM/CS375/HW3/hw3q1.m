clc
close all


% 1) Test inner-product, observe O(n) by estimating the rise/run in the plot
ns = [10^4, 10^5, 10^6, 10^7, 10^8];
for i=1:5
    n = ns(i);
    %Create two random vectors of size n
    u = rand(n,1); v = rand(n,1);

    %Measure time using the cputime command
    t = cputime;

    %Do the epxeriment 100 times
    for j = 1 : 100 %Inner Product
       ip = u'*v;
    end

    %average the times
    times(i) = (cputime-t)/100;
end
figure
plot(ns, times)
title("Vec-Vec Multiplication");
ax = gca;
ax.FontSize = 18; 
ylabel('Time','fontsize',18)
xlabel('N','fontsize',18)


% 2) Test mat-vec, observe the O(n^2) runtime for a single mat-vec 
%    by estimating the rise/run in the plot
ns = [100, 500, 1000, 5000, 10000]; 
for i=1:5
    n = ns(i);
    %Create random matrix and vectors of size n
    A = rand(n,n); v = rand(n,1);

    %Measure time using the cputime command
    t = cputime;

    %Do the epxeriment 100 times
    for j = 1 : 10 
       ip = A*v;
    end

    %average the times
    times(i) = (cputime-t)/100;
end
figure
plot(ns, times);
title("Mat-Vec Multiplication");
ax = gca;
ax.FontSize = 18; 
ylabel('Time','fontsize',18)
xlabel('N','fontsize',18)


% 3) Matrix-matrix multiplication
ns = [100, 500, 1000, 5000, 10000]; 
for i=1:5
    n = ns(i);
    %Create random matrix A and random matrix B
    A = rand(n,n); 
    B = rand(n,n);

    %Measure time using the cputime command
    t = cputime;

    %Do the epxeriment 100 times
    for j = 1 : 10 
       ip = A*B;
    end

    %average the times
    times(i) = (cputime-t)/100;
end
figure
plot(ns, times);
title("Mat-Mat Multiplication");
ax = gca;
ax.FontSize = 18; 
ylabel('Time','fontsize',18)
xlabel('N','fontsize',18)

