


-- Insert places
INSERT INTO public."Place" (id, "deskNumber", room) VALUES (1, 6, 'C');
INSERT INTO public."Place" (id, "deskNumber", room) VALUES (2, 5, 'B');

-- Insert roles
INSERT INTO public."Role" (id, title) VALUES (1, 'Mitglied');
INSERT INTO public."Role" (id, title) VALUES (2, 'Administrator');

-- Insert users
INSERT INTO public."User" (id, email, firstname, lastname, password, role) VALUES (1, 'user@user.ch', 'user', 'user', 'user', 1);
INSERT INTO public."User" (id, email, firstname, lastname, password, role) VALUES (2, 'admin@admin.ch', 'admin', 'admin', 'admin', 2);

-- Insert bookings
INSERT INTO public."Booking" (id, aprooved, "endTime", "startTime", place, "user") VALUES (1, false, '2022-03-10 12:15:50', '2022-03-10 12:15:50', 1, 2);
INSERT INTO public."Booking" (id, aprooved, "endTime", "startTime", place, "user") VALUES (2, false, '2022-03-10 12:15:50', '2022-03-10 12:15:50', 2, 1);

-- Insert kaffeeLog
INSERT INTO public."KaffeeLog" (id, "dateTime", "user") VALUES (1, '2022-03-10 12:15:50', 1);
INSERT INTO public."KaffeeLog" (id, "dateTime", "user") VALUES (2, '2022-03-10 12:17:50', 1);
