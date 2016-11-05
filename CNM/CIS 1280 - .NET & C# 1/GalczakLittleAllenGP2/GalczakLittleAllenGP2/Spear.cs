//Alexander Little
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GalczakLittleAllenGP2
{
    class Spear : GameObject, IWeapon, IPortable
    {
        int maxDamage;
        Random attackRand;//random number generator used for hit chance and damage output
        public int Size
        {
            get { return Size; }
            set { Size = 3; }
        }

        public int Attack(double range, double wind, double light)
        {
            double rangeProb = 0;

            if (range == 5) //this range will be determined by relative location of target to attacker (if that isn't determined by map placement, what is it determined by?)
            {
                rangeProb = .80;
            }
            else if (range == 25)
            {
                rangeProb = .70;
            }
            else if (range == 50)
            {
                rangeProb = .65;
            }
            else if (range == 100)
            {
                rangeProb = .30;
            }
            else if (range == 500)
            {
                rangeProb = .05;
            }
            else if (range == 1000)
            {
                rangeProb = .00;
            }
            //light = .3;
            //wind = .7;

            double hitChance = rangeProb * (1 - wind) * (1 - light);

            if (attackRand.NextDouble() < hitChance) //no hit
            {
                return attackRand.Next(1, maxDamage);
            }
            else //did hit, returns a random amount of damage between 1 and the max
            {
                return 0;
            }
        }

        public Spear() : base ("Sharpened Spear - best ranges 5-50")
        {
            attackRand = new Random();
            maxDamage = 20;
        }

        public override string ToString()
        {
            return base.ToString();
        }
    }
}