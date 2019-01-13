% MATH464 HW3 Q4 and Q5

% Initialization for our data and spaces.
X = [2 3 1.5 -1 0 3.6 .7 4.1 1.9 5]';
Y = [.1 2.7 -1.1 -5.5 -3.4 3 -2.8 4 -1.9 5.5]';
x = linspace(min(X),max(X));
num_of_polynomials = 9;

% Creating a list of matrices corresponding to the degree of each
% polynomial.
full_poly = [X.^0 X.^1 X.^2 X.^3 X.^4 X.^5 X.^6 X.^7 X.^8 X.^9];
poly_matrix_list = cell(1,100);
for i = 1:num_of_polynomials
    polynomial = full_poly(:,1:(i+1));
    c = polynomial\Y;
    c = flipud(c); % Reverse ordering of matrix
    poly_matrix_list{i} = polyval(c,x);
end

% Make 9 subplots, one for each degree polynomial
figure
for i = 1:num_of_polynomials

    % Actual plot of points vs. the least squares fit
    subplot(3,3,i);
    hold on
    plot(X,Y,'o');
    plot(x,poly_matrix_list{i});
    hold off
    
    % Graph details
    xlabel('x');
    ylabel('y');
    title(strcat('Least Squares fit, poly x^',num2str(i)));
    axis([-2 6 -7 7]);
    legend('Location','northwest');
    legend('data', strcat('x^',num2str(i)));

end

% 2nd figure that shows all the polynomial fits in one plot.
figure
xlabel('x');
ylabel('y');
title('Least Squares fit of polynomials x^1 to x^9');
hold on
plot(X,Y,'*','linewidth',5);

for i = 1:num_of_polynomials
    plot(x,poly_matrix_list{i});
end

axis([-2 6 -7 7]);
hold off
