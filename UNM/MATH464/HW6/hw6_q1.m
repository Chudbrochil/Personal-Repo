A = [2 1;-4 -2];
B = [2 0 1; -4 0 -2; -4 0 -2];

% Matrix A, (Part A)
jordan(A)
A*A

T_a = [1 0; -2 1]
J_a = [0 1; 0 0];

A * T_a;
T_a * J_a;

% for 1B
T_b = [2 1 0; -4 0 1; -4 0 0]
inv(T_b)
J_b = [0 1 0; 0 0 0; 0 0 0];

T_b * J_b * inv(T_b)

