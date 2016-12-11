// Anthony Galczak - agalczak@cnm.edu
// Program 5 - Snowboard Database

using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace GalczakP5.Models
{
    public class Snowboard
    {
        public string BindingOption { get; set; }
        public string ImageFileName { get; set; }
        public bool IncludeBindings { get; set; }
        public bool IsRegularFoot { get; set; }
        public double Length { get; set; }
        public int SnowboardID { get; set; }
        public double Width { get; set; }
    }
}