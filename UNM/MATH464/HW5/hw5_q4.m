% Do this for many matrices.
dim = 3;
%A = [4 -6 7; 2 0 5; -10 2 5] % From this problem (#4)
%A = [3 1 -1; -1 1 1; 2 2 0] % From problem #3
A = [1 2 3; 4 5 6; 7 8 9]

% Compute Pa(A) = -(A^3 + p2A^2 + p1*A + p0I)
characteristic_poly = poly(A)

p0 = characteristic_poly(4);
p1 = characteristic_poly(3);
p2 = characteristic_poly(2);

I = eye(3);
paA = -(A^3 + p2*A^2 + p1*A + p0 * I)