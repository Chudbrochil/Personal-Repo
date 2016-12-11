using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace SportsStatistics3.Models
{
    public class Team
    {
        public string TeamName { get; set; }
        public int Wins { get; set; }
        public int Losses { get; set; }
        public float WinPct { get; set; }
        public string RealName { get; set; }
        public string UserName { get; set; }
        public string Email { get; set; }
        public Boolean hasPaid { get; set; }
        public float AmountPaid { get; set; }
        public Boolean IsCommissioner { get; set; }
        public int TeamID { get; set; }

        public Team()
        {
            UserName = "Nobody";
        }
    }
}