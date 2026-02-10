-- Question 1
INSERT INTO question (question, duration) VALUES ('What is the size of a "char" type in C?', 15);
INSERT INTO answer (question_id, content, is_correct) VALUES
    (currval('question_question_id_seq'), '1 byte', TRUE),
    (currval('question_question_id_seq'), '2 bytes', FALSE),
    (currval('question_question_id_seq'), '4 bytes', FALSE),
    (currval('question_question_id_seq'), 'Depends on the compiler', FALSE);

-- Question 2
INSERT INTO question (question, duration) VALUES ('Which operator is used to get the address of a variable?', 15);
INSERT INTO answer (question_id, content, is_correct) VALUES
    (currval('question_question_id_seq'), '*', FALSE),
    (currval('question_question_id_seq'), '&&', FALSE),
    (currval('question_question_id_seq'), '&', TRUE),
    (currval('question_question_id_seq'), '->', FALSE);

-- Question 3
INSERT INTO question (question, duration) VALUES ('What will be the output of: printf("%d", 5 / 2);', 20);
INSERT INTO answer (question_id, content, is_correct) VALUES
    (currval('question_question_id_seq'), '2.5', FALSE),
    (currval('question_question_id_seq'), '2', TRUE),
    (currval('question_question_id_seq'), '3', FALSE),
    (currval('question_question_id_seq'), 'Error', FALSE);

-- Question 4
INSERT INTO question (question, duration) VALUES ('Which function is used to allocate memory dynamically in C?', 15);
INSERT INTO answer (question_id, content, is_correct) VALUES
    (currval('question_question_id_seq'), 'alloc()', FALSE),
    (currval('question_question_id_seq'), 'memalloc()', FALSE),
    (currval('question_question_id_seq'), 'malloc()', TRUE),
    (currval('question_question_id_seq'), 'new()', FALSE);

-- Question 5 (Continued)
INSERT INTO question (question, duration) VALUES ('What is the result of the expression: 10 != 10?', 15);
INSERT INTO answer (question_id, content, is_correct) VALUES
    (currval('question_question_id_seq'), 'True (1)', FALSE),
    (currval('question_question_id_seq'), 'False (0)', TRUE),
    (currval('question_question_id_seq'), '10', FALSE),
    (currval('question_question_id_seq'), 'Error', FALSE);

-- Question 6
INSERT INTO question (question, duration) VALUES ('What does the "continue" statement do in a loop?', 15);
INSERT INTO answer (question_id, content, is_correct) VALUES
    (currval('question_question_id_seq'), 'Exits the loop', FALSE),
    (currval('question_question_id_seq'), 'Skips the rest of the current iteration', TRUE),
    (currval('question_question_id_seq'), 'Restarts the entire program', FALSE),
    (currval('question_question_id_seq'), 'Pauses the loop execution', FALSE);

-- Question 7
INSERT INTO question (question, duration) VALUES ('Given "int a = 5; int *p = &a;", what does "*p" represent?', 20);
INSERT INTO answer (question_id, content, is_correct) VALUES
    (currval('question_question_id_seq'), 'The memory address of a', FALSE),
    (currval('question_question_id_seq'), 'The value 5', TRUE),
    (currval('question_question_id_seq'), 'The size of an integer', FALSE),
    (currval('question_question_id_seq'), 'The address of the pointer p', FALSE);

-- Question 8
INSERT INTO question (question, duration) VALUES ('Which of the following is the correct way to define a structure in C?', 20);
INSERT INTO answer (question_id, content, is_correct) VALUES
    (currval('question_question_id_seq'), 'struct { int x; };', TRUE),
    (currval('question_question_id_seq'), 'object { int x; };', FALSE),
    (currval('question_question_id_seq'), 'class { int x; };', FALSE),
    (currval('question_question_id_seq'), 'type struct { int x; }', FALSE);

-- Question 9
INSERT INTO question (question, duration) VALUES ('What will "printf("%zu", sizeof(int));" typically output on a modern 64-bit system?', 15);
INSERT INTO answer (question_id, content, is_correct) VALUES
    (currval('question_question_id_seq'), '2', FALSE),
    (currval('question_question_id_seq'), '4', TRUE),
    (currval('question_question_id_seq'), '8', FALSE),
    (currval('question_question_id_seq'), '1', FALSE);

-- Question 10
INSERT INTO question (question, duration) VALUES ('Which standard library header is required to use the "exit()" function?', 15);
INSERT INTO answer (question_id, content, is_correct) VALUES
    (currval('question_question_id_seq'), '<stdio.h>', FALSE),
    (currval('question_question_id_seq'), '<stdlib.h>', TRUE),
    (currval('question_question_id_seq'), '<string.h>', FALSE),
    (currval('question_question_id_seq'), '<conio.h>', FALSE);