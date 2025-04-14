CREATE TABLE public.likes (
  id serial NOT NULL PRIMARY KEY,
  recipe_id INTEGER REFERENCES recipes(id),
  time_stamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  user_id INTEGER REFERENCES users(id)
);