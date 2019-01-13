A = [7/5 1/5; -1 1/2];

% Part A
Ainf = A^1000000

S = [1 1; -5/2 -2]
D = [9/10 0; 0 1]
Dinf = D^1000000

S * Dinf * inv(S)

% Part B
%syms x
x = 1
B = [(1 + x)/2, (-1 + x)/2; (-1 + x)/2, (1 + x)/2]
Binf = B^1000000
jordanB = jordan(B)
eigB = eig(B)


T = [-1 1; 1 1]
J = [1 0; 0 x];

T * J * inv(T)

Jinf = J^1000000

T * Jinf * inv(T)

