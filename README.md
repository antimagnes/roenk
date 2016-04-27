roenk
=====

Goals
-----

Should be some kind of log analyzer helping with our daily tasks from a "logfile-only-feedback" environment coming
from test machines with unsynchronized clocks.

* "Synchronize" log files based on timestamp (e.g. specify one row from each log manually, that happened at the same
time and adjust all the timestamp accordingly)
* Interlink two log files to see event chronologically from multiple log files
* Do all the above on-the-fly, ideally acting as an end-point for log frameworks to connect to

Maybe, if I figure out how and has any practical use
* Define event flows, that is expected log entry sequences

Disclaimer
----------
This is mainly a playground for me trying to keep the fire burning in a bleak world of Java/Swing programming. Want
to keep it platform independent since in my close proximity (hopefully) it will be used on Windows/Mac OS/Linux so in
my humble opinion Java is still the best tool for such a goal. I'd like to use JavaFX since I'll probably have to
learn it anyways in the near future. Trying to do it in Kotlin, lets see how that goes.
