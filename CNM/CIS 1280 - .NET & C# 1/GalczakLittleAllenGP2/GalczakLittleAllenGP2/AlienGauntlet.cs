using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GalczakLittleAllenGP2
{
    class AlienGauntlet : GameObject, IWeapon, IPortable
    {
        int maxDamage;
        Random attackRand;//random number generator used for hit chance and damage output

        public int Size
        {
            get { return Size; }
            set { Size = 2; }
        }

        public int Attack(double range, double wind, double light)
        {
            double rangeProb = 0;

            if (range == 5) //this range will be determined by relative location of target to attacker (if that isn't determined by map placement, what is it determined by?)
            {
                rangeProb = .30;
            }
            else if (range == 25)
            {
                rangeProb = .50;
            }
            else if (range == 50)
            {
                rangeProb = .65;
            }
            else if (range == 100)
            {
                rangeProb = .70;
            }
            else if (range == 500)
            {
                rangeProb = .90;
            }
            else if (range == 1000)
            {
                rangeProb = .95;
            }
            //light = .3;
            //wind = .7;

            double hitChance = rangeProb * (1 - wind) * (1 - light); //TODO: check for need to use modifiers for usual hitChance calculations for example.. if light works differently for alien gauntlet, add a modifier 

            if (attackRand.NextDouble() < hitChance) //no hit
            {
                return attackRand.Next(20, maxDamage);
            }
            else //did hit, returns a random amount of damage between 1 and the max
            {
                return 0;
            }
        }

        public AlienGauntlet() : base ("Alien Gauntlet - best ranges 100-1000")
        {
            attackRand = new Random();
            maxDamage = 80;           
        }

        public override string ToString()
        {
            return base.ToString();
        }

        int IPortable.Size
        {
            get
            {
                throw new NotImplementedException();
            }
            set
            {
                throw new NotImplementedException();
            }
        }
    }
}