CREATE TYPE swipe_type_enum AS ENUM ('LIKE', 'DISLIKE');

CREATE TABLE public.swipes (
  id serial NOT NULL PRIMARY KEY,
  recipe_id INTEGER REFERENCES recipes(id),
  time_stamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  user_id INTEGER NOT NULL REFERENCES users(id),
  swipe_type swipe_type_enum NOT NULL
);

CREATE INDEX idx_swipes ON public.swipes(user_id, swipe_type);