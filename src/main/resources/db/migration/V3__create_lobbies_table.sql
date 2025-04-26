CREATE TABLE public.lobbies (
  id serial NOT NULL PRIMARY KEY,
  join_code text UNIQUE,
  join_code_expiry TIMESTAMP NOT NULL
);