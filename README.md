Name: Samuel Naser
Partner: Hannah Gaertner
Assignment: SimpleDB Lab 4
-=-=-=-=-=-=-=-=-=-=-=-=-=-


1. Design Decisions

Through the course of this lab, we were asked to implement various features in order to implement query optimization features
in our database, which entailed estimating the costs of various internal operations, in addition to reordering those operations
according to their costs (in terms of the resultant cardinalities downstream the operator chain). I did not find it necessary
in Lab 4 to introduce intermediate helper-classes as I did in the previous labs.

The concept behind the B+ Tree algorithms was straightforward for the most part, once we understood both the desired behavior
of the B+ Tree and the interfaces through which to interact with the existing structure (e.g. the methods to iterate over BTreePage
objects, in both regular and reverse order, the semantics to interact with parent pages, and the differences in interface between
the BTreeInternalPage and the BTreeLeafPage). From the design standpoint, most of our implemented methods were some variation
on the trope of iterating through one page from one direction, iterating through the other page from the other direction,
and performing some operation on those elements (either Tuples or BTreeEntry's).



2. API Changes

When compared with Lab 2, there were less novel implementation routes on Lab 3. In terms of concrete API changes,
I have implemented the API exposed for the B+ Tree interfaces of the existing B+ Tree classes. Below is the aggregate
list of changed files added or changed Lab 3, and I will describe the specific differences (broken down as old classes with new methods
/ new functions) in the implementations below the list, as I did for the Lab 2 writeup:

- BTreeChecker.java
- BTreeEntry.java
- BTreeFile.java
- BTreeFileEncoder.java
- BTreeHeaderPage.java
- BTreeInternalPage.java
- BTreeLeafPage.java
- BTreePage.java
- BTreePageId.java
- BTreeRootPtrPage.java
- BTreeScan.java
- BTreeUtility.java
- BufferPool.java
- IndexOpIterator.java
- IndexPredicate.java
- BTreeDeadlockTest.java
- BTreeFileDeleteTest.java
- BTreeFileInsertTest.java
- BTreeFileReadTest.java
- BTreeHeaderPageTest.java
- BTreeInternalPageTest.java
- BTreeLeafPageTest.java
- BTreeNextKeyLockingTest.java
- BTreePageIdTest.java
- BTreeRootPtrPageTest.java


Among these, for the most part, we worked on implementing skeleton methods which were provided to us in the BTreeFile.java
class. The most significant changes to the existing APIs overall was obviously the introduction of B+ Tree related functionalitites.



3. Missing or Incomplete Elements of Code

NOTE: the DeleteTest is failing as a result of the previous Lab 2 code which we could not finish. Not sure if this will be
reflected in our grade for this project (since its unrelated) but thought I would write it here and make the grade aware.

Our team attempted the extra credit reverse iterator, but did not have time to write adequete tets to cover
its functionalities. This part of our code might be incorrect in certain edge cases.

One aspect of our project that we were unable to make work were the BTreeNextKeyLockingTest's (both subtests, including
nextKeyLockingTestLessThan and nextKeyLockingTestGreaterThan). The project description document stated that these tests should
pass by default from having implemented the previous Labs without correctly, but this was not the case for us.

In addition, there is a bug in the "BTreeFileInsertTest" which we were not able to isolate and eliminate before the project deadline.
The specific test which fails is "testSplitRootPage", but this puzzled our team, as the behavior related to splitting the root page
should have been handled by the provided code (and semeed to be covered in our implementation).

It is possible but unintended that some portions of the written code don't handle unexpected inputs with graceful failure, however, I attempted to throw the correct corresponding exceptions wherever applicable throughout this lab.



4. Time Taken

Lab 3 took our team around 10-12 hours to complete to the point where all tests were passing, and a bit longer to clean the code up.
The most difficult portion of Lab 3 for me was debugging the section of the BTreeFileInsertTest (testSplitRootPage) system
test which we were unable to solve.
