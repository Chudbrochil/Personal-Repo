lhsrhsMatrix = 3*12;
deltasMatrix = 2*12;
format long


for i = 2:12
    % Generate hilbert matrix given A.
    A = hilb(i);
    
    % Solve for x_num and x_tilde
    x = ones(i,1);
    b = A*x;
    x_num = A\b;
    x_tilde = x_num - x;

    % Left hand side (max-norm(x_tilde)/max-norm(x))
    LHS = norm(x_tilde,inf) / norm(x,inf);
    
    % Right hand side (condition number * machine epsilon)
    RHS = cond(A,inf) * eps;
    
    % Maximum norm of error (Note: This is the same as LHS)
    deltaN = norm(x - x_num, inf);
    
    % Populating the rows of our matrices
    lhsrhsMatrix(i,1) = i;
    lhsrhsMatrix(i,2) = LHS;
    lhsrhsMatrix(i,3) = RHS;
    deltasMatrix(i,1) = i;
    deltasMatrix(i,2) = deltaN;
end


% Setting up for plotting
LHScolumn = lhsrhsMatrix(:,2);
RHScolumn = lhsrhsMatrix(:,3);
deltascolumn = deltasMatrix(:,2);

% Displaying numerical values for left hand and right hand sides.
disp(LHScolumn(2:end));
disp(RHScolumn(2:end));
disp(deltascolumn(2:end));

% Setting titles
title('Relative error vs. condNum * eps');
xlabel('nxn size of matrix');
ylabel('numerical');

% Plotting the elements we want to see
hold on
plot1 = plot(LHScolumn);
plot2 = plot(RHScolumn);
plotLabel1 = "LHS";
plotLabel2 = "RHS";
legend([plot1,plot2], [plotLabel1, plotLabel2]);
grid on

% Setting x-axis and y-axis limits and setting y-axis to log scale.
xlim([2 12]);
ylim([1e-16 1e+2]);
set(gca, 'YScale', 'log');


