% Script for increasingly small perturbations of 3 matrices of type:
% one unique solution(A), no solution(B), infinitely many solutions(C)

% Ax = b is equivalent to A_1*x = A_2
A_1 = [1, 3, -2; -1, 6, -3; -1, 5, -2];
A_2 = [3; 2; 1];
B_1 = [-1, 3, -2; -1, 4, -3; -1, 5, -4];
B_2 = [1; 0; 0];
C_1 = [-1, 3, -2; -1, 4, -3; -1, 5, -4];
C_2 = [4; 5; 6];

% Initializing random 3x3 matrix.
R = rand(3)

% Looping over epsilon = 10^0 to epsilon = 10^-15
for i = 0:15
    epsilonR = R * 10^(-i);
    A_tilde = C_1 + epsilonR;
    disp("i = " + i)
    x = linsolve(A_tilde, C_2)
end
