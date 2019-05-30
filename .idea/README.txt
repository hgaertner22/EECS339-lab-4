To implement SimpleDb, the following strategies were utilized. Lists were used in most cases to
store groups of things due to their ease of iteration and access, as in the case of TupleDesc
needing to store several TDItems, catalog needing to store tables, and buffer pool needing to store
many pages to name a few. A new table class was created within Catalog in order to store and
identify the individual tables that make it up and so that these can more easily be manipulated by
other methods and classes. A separate class was also created in the case of the iterator for the heap
file. This allowed the individual required methods such as open(), next(), close(), etc. to be more
easily expressed and utilized; it was based off the dbFileIterator class.

Unfortunately the code does not pass the SeqScan system tests for a reason I could not pinpoint. The
SeqScan file itself seemed relatively self-explanatory to fill in and the iterator that worked with
it seemed to pass previous tests, making it so that I had a lot of trouble trying to debug this. Other
than this, however, the code is complete and working as expected.

This lab took a long time for me. The initial set-up took about five hours as I encountered several
issues (the peskiest one was eventually solved when I discovered the files downloaded from Canvas possessed
._ hidden files meant for Macs that my Windows computer could not understand, preventing anything from
running). After that it became a lot smoother. Refamiliarizing myself with Java and its capabilities took
some time, as well as exploring the files and all they had to offer/how they interacted with one another. This
latter part was especially true when I worked on the Buffer Page’s readPage method. All in all writing,
testing, and debugging the code for all the tests other than SeqScan took me about 12 hours. Following this I
had a lot of trouble with SeqScan and debugging it. There were so many tuples in play that it was hard to reach
the point where SeqScan would suddenly have an issue with one, and when that happened I could not figure out why
it was reacting the way it was. After over three hours of trying to work through this part alone I had to admit
defeat and face the fact that I wouldn’t know why things were going wrong until visiting office hours after
turning in the assignment.
