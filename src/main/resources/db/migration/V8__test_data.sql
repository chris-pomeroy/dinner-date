INSERT INTO public.lobbies
(id, created_at)
VALUES(1, '2025-04-30 23:19:16.735');

INSERT INTO public.users
(id, first_name, email, password_hash, session_id, lobby_id)
VALUES(1, 'Frodo', 'frodo@email.com', '$2a$10$4d2W6Ck0FSrzKe7.DGSHjuZMVg7Vkk14vbAueJqsVBuv9JDQNQSYW', 'eGWVCeD41GISXO1QGYpY9lqD7KCfb_BlHEyYVtEk0Cs', 1);

INSERT INTO public.users
(id, first_name, email, password_hash, session_id, lobby_id)
VALUES(2, 'Bilbo', 'bilbo@email.com', '$2a$10$94K/sERjLlEocBAZO5p7ZeS1KhUvPFg.uAHTXewFD7ddjNPrCgBGu', 'Qky7xxQmDv2D_ZhXQDhxvk9i4McHuSvYFRzsO8zRjEU', 1);