CREATE TABLE public.likes (
  id serial NOT NULL PRIMARY KEY,
  recipe_id INTEGER REFERENCES recipes(id),
  time_stamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);