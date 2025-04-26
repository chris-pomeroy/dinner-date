CREATE TABLE public.lobby_memberships (
  user_id INTEGER NOT NULL REFERENCES users(id),
  lobby_id INTEGER NOT NULL REFERENCES lobbies(id),
  PRIMARY KEY (user_id, lobby_id)
);