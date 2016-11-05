using System;
using TDDDemo;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace TDDDemoTest
{
    [TestClass]
    public class UtilitiesTest
    {
        [TestMethod]
        public void ShouldFindOneYInMysterious()
        {
            var stringToCheck = "mysterious";
            var stringToFind = "y";
            var expectedResult = 1;
            var classUnderTest = new TDDDemo.StringUtilities();
            var actualResult = classUnderTest.CountOccurences(stringToCheck, stringToFind);
            Assert.AreEqual(expectedResult, actualResult);
        }

        [TestMethod]
        public void ShouldfindTwoSInMysterious()
        {
            var stringToCheck = "mysterious";
            var stringToFind = "s";
            var expectedResult = 2;
            var classUnderIest = new StringUtilities();
            var actualResult = classUnderIest.CountOccurences(stringToCheck, stringToFind);
            Assert.AreEqual(expectedResult, actualResult);

           // Assert.AreEqual(2, x => StringUtilities.CountOccurrences("mysterious", "s"));
        }

        [TestMethod]
        public void SearchShouldBeCaseInsensitive()
        {
            var stringToCheck = "mySterious";
            var stringToFind = "s";
            var expectedResult = 2;
            var classUnderIest = new StringUtilities();
            var actualResult = classUnderIest.CountOccurences(stringToCheck, stringToFind);
            Assert.AreEqual(expectedResult, actualResult);
        }

        [TestMethod]
        public void ShouldReturnNegativeOneForNul()
        {
            string stringToCheck = null;
            string stringToFind = "s";
            int expectedResult = -1;
            StringUtilities classUnderIest = new StringUtilities();
            object actualResult = classUnderIest.CountOccurences(stringToCheck, stringToFind);
            Assert.AreEqual(expectedResult, actualResult);
        }


    }
}
