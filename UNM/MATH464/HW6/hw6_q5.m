A = [2 1; -1 0];
B = [0 1; -1 0];
C = [1.98 .99; -.99 0];

% The power that we are taking all these matrices to
n = 500;

a_norms = zeros(1,n);
b_norms = zeros(1,n);
c_norms = zeros(1,n);
x = linspace(1, n, n);

% Taking norms of matrices at each power.
for i = 1:n
    a_norm = norm(A^i);
    b_norm = norm(B^i);
    c_norm = norm(C^i);
    
    a_norms(i) = a_norm;
    b_norms(i) = b_norm;
    c_norms(i) = c_norm;
end


% Plotting the norms of the matrices to see behavior.

a_plot = plot(x, a_norms);
hold on
title("Norms of Matrix A, B, C at specified powers.")
xlabel("Powers 1 to 500")
ylabel("Norm (max singular value) of given Matrix")
b_plot = plot(x, b_norms);
c_plot = plot(x, c_norms);
hold off


legend([a_plot b_plot c_plot], "A norms", "B norms", "C norms")
    
    