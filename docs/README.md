# Stitch User Guide

  

  

**Stitch** ChatBot your productivity companion designed to streamline task management and deadlines. This guide will help you get started and make the most of Stitch’s features.

  

  

![Stitch ChatBot](https://github.com/jasmitha-05/ip/blob/master/docs/Ui.png?raw=true)

  

  

## Quick start

  

  

1. Ensure you have Java `17` or above installed in your Computer.

  

**Mac users:** Ensure you have the precise JDK version prescribed [here](https://se-education.org/guides/tutorials/javaInstallationMac.html).

  

2. Download the latest `.jar` file from [here](https://github.com/Jasmitha-05/ip/releases).

  

3. Copy the file to the folder you want to use as the _home folder_ for your own Stitch Chatbot.

  

4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar stitch.jar` command to run the application.

  

  

## Features

  

>  **Notes about the command format:**

  

>  - Words in `UPPER_CASE` are the parameters to be supplied by the user.

  

e.g. in `todo DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `todo read book`.

#### Types of input format allowed:  

>- **All commands are case-insensitive.**

  

e.g. `DEADline`/`deadline` and other combinations works just as fine

  

>- **Stitch chatbot can handle all inputs even those with extra spaces between the COMMAND, DESCRIPTION, FORMAT, DATE** to provide more flexibility for users

  

e.g. `COMMAND [any extra spacing] DESCRIPTION [any extra spacing] /FORMAT [any extra spacing] DATE`

  
Examples:

```

1. deadline          submit quiz        /by        2026-2-13 12:00 
2. todo        read book
3. event    project  meeting     /from     2026-2-13 12:00    /to          2026-2-17 14:00
4. mark       1
5. unmark     1
6. upcoming      3
7. search      2026-2-13
8. find     read
9. delete    2

```

  

**caution!!** however date format must strictly follow `yyyy-m-d H:m` exactly

  

>- If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.




### Adding Deadlines: `Deadline`

Add deadline task with specific date and time

**caution!!** date format must strictly follow `yyyy-m-d H:m` exactly


format: `deadline DESCRIPTION /by YYYY-M-D H:M`

#### Examples:

See [types of inputs allowed](#types-of-input-format-allowed) above for more details.

- `DEAdline submit quiz /by 2026-2-13 12:00 `
-  `deadline upload assignment /by 2026-2-13 12:00`

When done correctly, Stitch would **display the newly added task** with its completion status and **total number of tasks saved**

```

eg:
Got it. I've added this task:
[D][] submit quiz (by: Feb 13 2026, 12:00)
Now you have 1 task in the list.

```


### Adding Events: `Event`

Add event task with specific start and end date, time

**caution!!** date format must strictly follow `yyyy-m-d H:m` exactly

format: `event DESCRIPTION /from YYYY-M-D H:M /to YYYY-M-D H:M`

#### Examples:

See [types of inputs allowed](#types-of-input-format-allowed) above for more details.

- `EVEnt project meeting /from 2026-2-13 12:00 /to 2026-2-17 14:00 `
-  `event career fest /from 2026-2-13 12:00 /to 2026-2-18`

When done correctly, Stitch would **display the newly added task** with its completion status and **total number of tasks saved**
```

eg:
Got it. I've added this task:
[E][] project meeting (from: Feb 13 2026, 12:00 to: Feb 17 2026, 14:00)
Now you have 2 task in the list.

```


### Adding Todos: `Todo`

Add todo task

format: `todo DESCRIPTION`

#### Examples:

See [types of inputs allowed](#types-of-input-format-allowed) above for more details.

- `TOdO read book `
-  `todo upload assignment`

When done correctly, Stitch would **display the newly added task** with its completion status and **total number of tasks saved**

```

eg:
Got it. I've added this task:
[T][] read book
Now you have 3 task in the list.

```


### Marking tasks: `Mark`

User can mark task that they have completed

**caution!!** user can mark an already marked task
**caution!!** INDEX is **1-indexed** following the numbering shown when the list is displayed

format: `mark INDEX`

#### Examples:

See [types of inputs allowed](#types-of-input-format-allowed) above for more details.

- `MaRK 1 `
-  `mark 1 `

When done correctly, Stitch would display the task with the **updated completion status with the `X`**

 ```
 
eg:
Nice I've marked this task as done:
[D][X] submit quiz (by: Feb 13 2026, 12:00)

```


### Unmarking tasks: `Unmark`

User can mark task that they have completed

**caution!!** user can unmark an already unmarked task
**caution!!** INDEX is **1-indexed** following the numbering shown when the list is displayed

format: `mark INDEX`

#### Examples:

See [types of inputs allowed](#types-of-input-format-allowed) above for more details.

- `UNMaRK 1 `
-  `unmark 1 `

When done correctly, Stitch would display the task with the **updated completion status without the `X`**

```

eg:
OK, I've marked this task as not done yet:
[D][] submit quiz (by: Feb 13 2026, 12:00)

```


### Displaying List: `List`

User can ask Stitch to display all the task

format: `list`

#### Examples:

See [types of inputs allowed](#types-of-input-format-allowed) above for more details.

- `LiSt `
-  `list`

When done correctly, Stitch would **display all the available tasks and details** such as completion status, date and time if applicable.

```

eg:
Here are the tasks in your list:
1. [D][] submit quiz (by: Feb 13 2026, 12:00)
2. [E][] project meeting (from: Feb 13 2026, 12:00 to: Feb 17 2026, 14:00)
3. [T][] read book

```


### Deleting Tasks: `Delete`

User can ask stitch to delete a specific task

format: `delete INDEX`

**caution!!** INDEX is **1-indexed** following the numbering shown when the list is displayed

#### Examples:

See [types of inputs allowed](#types-of-input-format-allowed) above for more details.

- `DelETE 3 `
-  `delete 3`

When done correctly, Stitch would **display the delete task** and the **number** of remaining tasks

```

eg:
Noted. I've removed this task:
[T][] read book
Now you have 2 tasks in the list

```


### Finding tasks based on keywords: `Find`

User can ask stitch to display all tasks with matching description to the keyword given

**caution!!** only **ONE keyword** allowed

format: `find KEYWORD`

#### Examples:

See [types of inputs allowed](#types-of-input-format-allowed) above for more details.

- `FiND quiz `
-  `find project`

When done correctly, Stitch would display all tasks on that specific date

```

eg:
Here are the matching tasks in your list:
1. [D][] submit quiz (by: Feb 13 2026, 12:00)

```


### Searching tasks based on date: `Search`

User can ask stitch to display all tasks falling on a specific **date**

**caution!!** date format must strictly follow `yyyy-m-d` exactly

format: `search YYYY-M-D`

#### Examples:

See [types of inputs allowed](#types-of-input-format-allowed) above for more details.

- `SEArcH 2026-2-13 `
-  `search 2026-2-13`

When done correctly, Stitch would display all tasks on that specific date

```

eg:
Got it. Tasks on that date:
[D][] submit quiz (by: Feb 13 2026, 12:00)
[E][] project meeting (from: Feb 13 2026, 12:00 to: Feb 17 2026, 14:00)

```


### Reminder for upcoming tasks: `Upcoming`

User can ask Stitch to display all tasks due in the coming specified days from today

format: `upcoming DAYS`

**caution!!** DAYS is an integer >= 0

#### Examples:

See [types of inputs allowed](#types-of-input-format-allowed) above for more details.

- `UpCOMing 3 `
-  `upcoming 3`

When done correctly, Stitch would display all tasks due INTEGER days away from today

**_if today was Feb 13 2026_**
```

eg:
Upcoming tasks due within 3 days:
[D][] submit quiz (by: Feb 13 2026, 12:00)
[E][] project meeting (from: Feb 13 2026, 12:00 to: Feb 17 2026, 14:00)

```


### Say BYE to Stitch: `Bye`

User can always say bye

format: `Bye`

#### Examples:

See [types of inputs allowed](#types-of-input-format-allowed) above for more details.

- `BYe `
-  `bye`

If user says bye, Stitch will say bye to you back

```

Bye. Hope to see you soon!

```


### Saving the data

Stitch Chatbot data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.


### Editing the data file

Stitch Chatbot data are saved automatically as a txt file `[JAR file location]/data/stitch.txt`. Advanced users are welcome to update data directly by editing that data file.

**caution!!** If your changes to the data file makes its format invalid, Stitch will discard all data and start with an empty data file at the next run. Hence, it is recommended to take a backup of the file before editing it.

Furthermore, certain edits can cause Stitch to behave in unexpected ways (e.g., if a value entered is outside of the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.


## Command summary

| Action   | Format                                                       | Examples                                              |
|----------|--------------------------------------------------------------|-------------------------------------------------------|
| todo     | todo DESCRIPTION                                             | todo upload assignment                                |
| event    | event DESCRIPTION /from YYYY-M-D H:M /to YYYY-M-D H:M        | event career fest /from 2026-2-13 12:00 /to 2026-2-18 18:00 |
| deadline | deadline DESCRIPTION /by YYYY-M-D H:M                        | deadline upload assignment /by 2026-2-13 12:00       |
| list     | list                                                         | list                                                  |
| mark     | mark INDEX                                                | mark 1                                                |
| unmark   | unmark INDEX                                               | unmark 1                                              |
| delete   | delete INDEX                                                 | delete 1                                              |
| find     | find KEYWORD                                                 | find fest                                             |
| search   | search YYYY-M-D                                              | search 2026-2-13                                      |
| upcoming | upcoming DAYS                                                | upcoming 3                                            |
