function[A,b] =  generate_SPD_mat_and_rhs_vec(n, a)
%Input:
%n: Positive Integer
%a: Reaction term

%Outputs:
%A: nxn matrix
%b: n vector

h = 1/(n+1);
x = (h:h:(1-h))';

my_two = -2*ones(n,1);
my_ones = ones(n-1,1);
scal_fac = (1/(h*h));
A = (diag(my_two) + diag(my_ones,1) + diag(my_ones,-1));
A = -scal_fac*A + a*speye(n);

b = sin(2*pi*x);
b(1) = b(1) - scal_fac;
b(end) = b(end) - scal_fac;
