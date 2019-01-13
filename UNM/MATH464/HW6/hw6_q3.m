A = [2 1; -1 0];
B = [0 1; -1 0];
C = [1.98 .99; -.99 0];

% Matrix A
jordan(A)
T_a = [1 1; -1 0]
inv(T_a) * A * T_a

% Matrix B
jordan(B)
T_b = [1 1; i -i]
inv(T_b) * B * T_b
T_b * [i 0; 0 -i] * inv(T_b)

% Matrix C
jordan(C)
T_c = [1 1; -1 1/99]
inv(T_c) * C * T_c



