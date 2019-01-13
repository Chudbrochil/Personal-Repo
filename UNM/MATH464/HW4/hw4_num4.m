A = [1 1 1 1; 1 -1 1 0; -1 0 0 1; 0 -1 0 1; -1 1 0 1; 1 0 -1 1];

fprintf("Rank of A: %d\n", rank(A))
fprintf("Norm of A: %d\n", norm(A))

% We know that dist(A, Rj) = sigma(j+1)
[U, S, V] = svd(A);
fprintf("Distance from A to Rank 1 matrix: %f\n", sqrt(S(2,2)))
fprintf("Distance from A to Rank 1 matrix: %f\n", sqrt(S(3,3)))
fprintf("Distance from A to Rank 1 matrix: %f\n", sqrt(S(4,4)))