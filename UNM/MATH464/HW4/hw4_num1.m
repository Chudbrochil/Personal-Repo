A = [1, 0; 0, 1; 1, 0];

[U,S,V] = svd(A)
fprintf("Sigma 1 =  %f\n", S(1,1))

