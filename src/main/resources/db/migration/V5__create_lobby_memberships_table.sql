CREATE TABLE public.lobby_memberships (
  id serial NOT NULL PRIMARY KEY,
  user_id INTEGER NOT NULL REFERENCES users(id),
  lobby_id INTEGER NOT NULL REFERENCES lobbies(id)
);