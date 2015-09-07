ADDITIONAL SPECIFICATION

Input
 - file: payments (user can specify filename)
 - file: exchange rates for USD ("er_USD")
 - console: payments

Output
 - console: net amounts

File Format
 - in accordance with the requirements
 - there can be empty lines, these will be ignored


Error handling
 Program reports errors to user and enables try again or continues. For example if program don't find the file with payments user entered, program enables user to enter the filename again. On the other hand if exchange rates file isn't found, program reports it and continues. If program is in phase of entering payments by user, it reacts on invalid input by display error msg with some help.


Precision
 The exchange rates are indicative only. Also payments are represent by integers, not e.g. Java type BigDecimal which would be more suitable for money, so application does not count with big numbers or cents as the sample input and output also comprise only small integers.


Because of vacuum around the task (not much requirements or customer knowledge) there is some imbalance in implementation. For example application keeps a record of payments as first sentence of task says but there is no other use of it or store it as there is no such requirements. So in case of no other functionality extension, it might free the memory after load from file and process.




BUILD AND RUN

The project is created in NetBeans, so other way is import it by NetBeans (or other IDE).
Also you can use build.xml and build-impl.xml NetBeans generated for ANT.

