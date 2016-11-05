//TODO: -3 no comment header. RJG
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace GalczakP2.Models
{
    public class InvestmentCalc
    {

        public InvestmentCalc()
        {
            // Setting default values for variables in case the default constructor is used
            // Principal: 1000$, 10 years, 5% interest, 1 compounds per year
            this.principal = 1000;
            this.years = 10;
            this.interest = 0.05;
            this.cmpPerYr = 1;
        }

        // Overloaded constructor
        public InvestmentCalc(double principal, int years, 
            double interest, int perYear)
        {
            this.principal = principal;
            this.years = years;
            this.interest = interest;
            this.cmpPerYr = perYear;
        }


        private int cmpPerYr;

        [Required, Range(1, 365)]
        public int CmpPerYr
        {
            get { return cmpPerYr; }
            set
            {
                //TODO: -5 set value then call calc. RJG
                Calc();
                cmpPerYr = value;
            }
        }

        private double interest;

        [Required, Range(0.01, 1.00)]
        public double Interest
        {
            get { return interest; }
            set
            {
                //TODO: set value then call calc. RJG
                Calc();
                interest = value;
            }
        }

        private double principal;

        [Required, Range(.01, Double.MaxValue)]
        public double Principal
        {
            get { return principal; }
            set
            {
                //TODO: set value then call calc. RJG
                Calc();
                principal = value;
            }
        }

        private int years;

        [Required, Range(1, 500)]
        public int Years
        {
            get { return years; }
            set
            {
                //TODO: set value then call calc. RJG
                Calc();
                years = value;
            }
        }

        private double futureValue;

        public double FutureValue
        {
            get
            {
                //TODO: -5 never call calc from get. Always from sets. RJG
                Calc();
                return futureValue;
            }
        }

        private void Calc()
        {
            /* http://www.thecalculatorsite.com/articles/finance/compound-interest-formula.php
            A = P (1 + r/n) ^ nt
            A = the future value of the investment/loan, including interest
            P = the principal investment amount (the initial deposit or loan amount)
            r = the annual interest rate (decimal)
            n = the number of times that interest is compounded per year
            t = the number of years the money is invested or borrowed for */

            // ((1 + Interest/CmpPerYr)) ^ (CmpPerYr * Years)) * Principal
            
            this.futureValue = Math.Pow((1 + (Interest / CmpPerYr)), (CmpPerYr * Years)) * Principal;

        }



    }
}
 