CREATE TABLE public.dislikes (
  id serial NOT NULL PRIMARY KEY,
  recipe_id INTEGER REFERENCES recipes(id),
  time_stamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  user_id INTEGER NOT NULL REFERENCES users(id),
  lobby_id INTEGER REFERENCES lobbies(id)
);