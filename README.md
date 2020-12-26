# Master of Information Technology (LMIF) Code

This is a historical repository for code I wrote when I was a student at
[University of South Australia][], studying for a Master of Information
Technology, which had the program code of LMIF (hence the repository name). It
was a 1.5-to-2-year long post-graduate coursework-based programme created for
graduates from other disciplines to learn about information technology and
software. The LMIF program does not seem to be available any more at the
university, but I promise it did actually exist.

The code contained herein is learner's code, and certainly not meant to be an
example of code I would write today. But, we all start from somewhere, and I
figured that I would rather have this content up publicly to document that every
journey has its beginning, and that we never start writing "good" code.

From a personal perspective, creating this repository is the code-equivalent of
looking through old photo albums, so I have found it fun to re-visit the code,
and the memories associated to that time of my life.

Having said that, though, a lot of the code here makes present me reel back in
horror and wonder how it even works. It certainly makes me respect my professors
even more given that they graded whole classes worth of code that looks like
this.

## Language Versions

### Java

All the Java code was originally created to run with [J2SE 1.4][], but
everything is confirmed working with [OpenJDK][] 15.0.1. See the README file
for each program for how to compile and run the programs.

### C++

I do not remember what version of `g++` the C++ code was originally compiled
against, but I can confirm that the code runs with the following version:

```sh
$ g++ --version
Configured with: --prefix=/Applications/Xcode.app/Contents/Developer/usr --with-gxx-include-dir=/Library/Developer/CommandLineTools/SDKs/MacOSX10.15.sdk/usr/include/c++/4.2.1
Apple clang version 12.0.0 (clang-1200.0.32.28)
Target: x86_64-apple-darwin19.6.0
Thread model: posix
InstalledDir: /Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin
```

## _Caveat Lector_

_[Reader Beware][Caveat Lector]_: although every effort has been made to ensure
that the code _can_ be compiled and run, this does not mean that it runs
completely as intended or without bugs. I have fixed and improved nothing.

## Content Changes

This code is intended to stand as a historical record, rather than something I
would iterate and improve upon. Therefore, my intention is to forego making any
unnecessary changes (like re-formatting code or removing commented-out debug
lines) except in the following circumstances where I felt they were required:

- Scrub personally identifiable information.
- If the code does not compile or run, make the least amount of changes that
  would enable it to do so.

Where I have made code changes, the original lines of code have been commented
out and annotated with an `ORIG:` identifier, keeping the original formatting as
much as possible.

## Specification Omissions

Since the specifications for each of the code problems in this repository were
created by staff at a for-profit educational institution as part of a
university curriculum, I am going to assume that the original text for the
specifications is still covered by copyright.

Therefore, I have only produced a summary or an extract of the original
specifications for each problem. I cannot imagine that the specifications for
anything I was taught would still be in use now, but it is better to be safe
than sorry.

## Content Omissions

I did have subjects where there was no code written, but rather lots of essays
and reports instead. Given that this is a GitHub repository, I have elected to
focus only on content from subjects where there specifically was code written.

Furthermore, I omitted any code where there were dependencies on:

- requiring a _significant amount_ of professor-supplied code, of which I cannot
  verify the copyright status, that formed a _core part_ of application
  functionality
- reading and understanding a report/essay to get all the context
- making requests to a university server that no longer exists in order to get
  an assignment to run as expected
- code co-owned or co-written by other classmates
- third-party libraries/applications that either no longer exist or are
  platform-dependent

## List of Omitted Subjects

There were other subjects as part of the LMIF course, but content from them
have been omitted for the following reasons:

### Computer and Internet Technology

This subject was an introductory course to acquaint students with current
technology in computer hardware, software, operating systems, and the Internet.

Part of the course involved building some basic [HTML][] pages, but they
required university servers to submit against. The markup can not stand alone,
and so I have omitted content from this subject.

### Mathematics for Computing

This subject aimed to develop aspects of discrete mathematics relevant to
computer science and related disciplines.

There were no coding components, with the deliverables being hand-written
assignments containing problem solving, the scans of which are probably not of
interest for this repository.

### Objects and Algorithms

This subject covered fundamental programming [data structures][] and algorithms,
algorithm strategies and analysis, [recursion][], and basic [computability
theory][].

Normally, I would absolutely have included code from this subject as there was
quite a lot of it. However, the assignments had a _significant amount_ of code
supplied by the professor, where part of the work involved essentially "filling
in the blanks". So, this precludes me from including any code from that subject
in this repository due to not knowing the copyright status of that code.

### Object-Oriented System Development

This subject primarily covered the software development process itself, focusing
on documentation and [UML][] diagramming.

Hence, there were no code deliverables that would belong in this repository.

### Network Technology M

This subject primarily covered computer networking basics, including [TCP/IP][],
[LAN][]s, [WAN][]s, wireless networks etc. It also covered general network
security and management.

All the assignments were essay-based, and hence there were no code deliverables
that would belong in this repository.

### Software Development Process Management M

This subject primarily covered systems development life-cycle models, project
management tools, and software process management principles.

The assignments consisted of three stages of a group project deliverable of an
algorithm visualiser [Java applet][], as well all of the documentation and
diagrams involved in the application's design.

Since I was the only native English speaker in my group, the best way I could
contribute was to be in charge of all the deliverables that required English.
Therefore, I did not end up writing any code for the project subject. Hence,
there is nothing I created that I would include in this repository.

### Databases M

This subject was an introduction to database management and design.

The assignments did include submission of [SQL][] files, but since the
university made us use [Oracle DBMS][], I cannot currently confirm whether the
files still work, and hence I have omitted them from this repository.

[data structures]: https://en.wikipedia.org/wiki/Data_structure
[Caveat Lector]: https://en.wiktionary.org/wiki/caveat_lector
[computability theory]: https://en.wikipedia.org/wiki/Computability_theory
[HTML]: https://en.wikipedia.org/wiki/HTML
[J2SE 1.4]: https://en.wikipedia.org/wiki/Java_version_history#J2SE_1.4
[Java Applet]: https://en.wikipedia.org/wiki/Java_applet
[LAN]: https://en.wikipedia.org/wiki/Local_area_network
[OpenJDK]: https://openjdk.java.net/
[Oracle DBMS]: https://en.wikipedia.org/wiki/Oracle_Database
[recursion]: https://en.wikipedia.org/wiki/Recursion
[SQL]: https://en.wikipedia.org/wiki/SQL
[TCP/IP]: https://en.wikipedia.org/wiki/Internet_protocol_suite
[UML]: https://en.wikipedia.org/wiki/Unified_Modeling_Language
[University of South Australia]: https://www.unisa.edu.au/
[WAN]: https://en.wikipedia.org/wiki/Wide_area_network
