// Anthony Galczak - agalczak@cnm.edu
// Program 3 - Professional Website

using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Professional_Website.Models
{
    public class ContactInfo

    {
        private String comments;

        public String Comments
        {
            get { return comments; }
            set { comments = value; }
        }

        private int contactInfoID;

        public int ContactInfoID
        {
            get { return contactInfoID; }
            set { contactInfoID = value; }
        }

        private String email;

        public String Email
        {
            get { return email; }
            set { email = value; }
        }

        private String firstName;

        public String FirstName
        {
            get { return firstName; }
            set { firstName = value; }
        }

        private String lastName;

        public String LastName
        {
            get { return lastName; }
            set { lastName = value; }
        }

        private bool isPhoneApp;

        public bool IsPhoneApp
        {
            get { return isPhoneApp; }
            set { isPhoneApp = value; }
        }

        private bool isWebApp;

        public bool IsWebApp
        {
            get { return isWebApp; }
            set { isWebApp = value; }
        }

        private bool isWindowsApp;

        public bool IsWindowsApp
        {
            get { return isWindowsApp; }
            set { isWindowsApp = value; }
        }

        private String typeOfRequest;

        public String TypeOfRequest
        {
            get { return typeOfRequest; }
            set { typeOfRequest = value; }
        }


    }
}