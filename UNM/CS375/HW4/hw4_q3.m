% 3a)
n = 100;
a = 200;

[A,b] = generate_SPD_mat_and_rhs_vec(n, a);

height = size(A,1);

% N = nnz(X) returns the number of nonzero elements in matrix X.
avg_num_zeros_per_row = nnz(A) / height
