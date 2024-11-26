package com.company;


import org.junit.platform.suite.api.*;

//@SelectPackages({"com.company"})
//@ExcludePackages({"com.company"})
//@IncludePackages({"com.company"})
//@IncludeTags({"development"})
//@ExcludeTags({"development"})
@Suite
@SelectClasses({
        CalculatorTest.class,
        TransactionServiceTest.class
})
public class TestSuit {
}
