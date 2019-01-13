% MATH 464 HW3 Q3
% Determine the best least squares fit of the data by a
% formula of the form b = x0 + x1a1 + x2a2

b = [1 2 0 -1];
a1 = [1 0 1 2];
a2 = [0 1 1 -1];
x = ones(4,1);
A = [x, a1', a2']
x = inv(A'*A)*A'*b'

