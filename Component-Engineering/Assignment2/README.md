# Component Engineering - Assignment 2

## Summarised Original Specifications

Write a program that can encrypt and decrypt a file using a
[Substitution Cipher][] or an [XOR Cipher][].

## Compile/Run

```sh
javac *.java
java Crypt -e -M KeyMono0 plain.txt hidden.txt
# confirm hidden.txt same as plain.txt
java Crypt -d -M KeyMono0 hidden.txt plain2.txt
# confirm plain2.txt same as hidden.txt
java Crypt -e -M KeyMono1 plain.txt hidden.txt
# confirm hidden.txt is encrypted
java Crypt -d -M KeyMono1 hidden.txt plain2.txt
# confirm plain2.txt is plaintext
java Crypt -e -M KeyXOR0 plain.txt hidden.txt
# confirm hidden.txt same as plain.txt
java Crypt -d -M KeyXOR0 hidden.txt plain2.txt
# confirm plain2.txt same as hidden.txt
java Crypt -e -M KeyXOR1 plain.txt hidden.txt
# confirm hidden.txt is encrypted
java Crypt -d -M KeyXOR1 hidden.txt plain2.txt
# confirm plain2.txt is plaintext
```

## External Content

The `Crypt.java` file and the key files (`KeyMono0`, `KeyMono1`, `KeyXOR0`,
`KeyXOR1`) were written by the subject professor. `Crypt.java` essentially
provides the command line interface logic, and delegates off
encryption/decryption functionality to the other classes that I wrote.

Since they only represent a small part, code-volume-wise, of the application, I
am including them in this repository as-is so this application can be run.
However, I am happy to remove them (and consequently this assignment) if there
are any copyright issues with posting it.

[Substitution Cipher]: https://en.wikipedia.org/wiki/Substitution_cipher
[XOR Cipher]: https://en.wikipedia.org/wiki/XOR_cipher
