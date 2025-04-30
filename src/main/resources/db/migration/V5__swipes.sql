CREATE TYPE swipe_type_enum AS ENUM ('LIKE', 'DISLIKE');

CREATE TABLE public.swipes (
  id serial NOT NULL PRIMARY KEY,
  recipe_id INTEGER NOT NULL REFERENCES recipes(id),
  user_id INTEGER NOT NULL REFERENCES users(id),
  swipe_type swipe_type_enum NOT NULL,
  created_at TIMESTAMP NOT NULL,
  time_zone text NOT NULL CHECK (now() AT TIME ZONE time_zone IS NOT NULL),
  local_date DATE NOT NULL
);

CREATE INDEX idx_swipes ON public.swipes(user_id, swipe_type);