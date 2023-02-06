
insert into account( id, name ,username ,password) values ('b5607d38-8fc1','test','test@gmail.com','password' );

insert into forum(id ,username ,title) values (1,'test@gmail.com','forum item test' );

insert into chat_message(id, username ,message, forum_id) values (1,'test2@gmail.com','jakas wiadomosc 1', 1);
insert into chat_message(id, username ,message, forum_id) values (2,'test1@gmail.com','jakas wiadomosc 2', 1);

insert into dirorfile(id ,path ,name  ,link  ,permisions ,username  ,groupname ,is_directory ,size ,text ,time ) values (1,'/testfolder', 'testdir', 2, 'drwxr-xr-x','root', 'group','true', 4096, '', 'Aug 7 10:51');
insert into dirorfile(id ,path ,name  ,link  ,permisions ,username  ,groupname ,is_directory ,size ,text ,time ) values (2,'/testfolder', 'testfile.txt', 1, '-rw-r--r--','root', 'group','false', 4096, 'some string', 'Aug 8 11:51');
