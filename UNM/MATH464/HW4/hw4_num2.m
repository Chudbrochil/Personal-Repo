% Part A)
A = [-2, 11; -10, 5];
iterations = 500
range = linspace(0, 2*pi, iterations);
image = A*[cos(range); sin(range)];
p1 = plot(image(1,:),image(2,:))
hold on

% Part B)
[U, S, V] = svd(A)
sig1 = S(1,1) * U(1,:);
sig2 = S(2,2) * U(2,:);

% This is plotv(), I didn't want to import Neural Networks Toolbox
p2 = plot([0, sig1(1)], [0,sig1(2)])
p3 = plot([0, sig2(1)], [0,sig2(2)])
legend([p1,p2,p3], "Image A(cos, sin)", "sig1u1", "sig2u2");, 

% Part C)
A = norm(sig1);
B = norm(sig2);
area = pi.*A.*B

hold off
axis square

