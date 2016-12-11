using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace SportsStatistics3.Models
{
    public class League
    {
        public int LeagueID { get; set; }
        public float InitialFee { get; set; }
        public string CommissionerName { get; set; }
        public string Rules { get; set; }
        public int NumTeams { get; set; }
        public int MaxTeams { get; set; }
        public List<Team> Teams { get; set; }

        ApplicationDbContext db;

        public League()
        {
            db = new ApplicationDbContext();
            Teams = db.Teams.ToList();
        }
    }
}