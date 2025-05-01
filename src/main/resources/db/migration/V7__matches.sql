CREATE TABLE public.matches (
  id serial NOT NULL PRIMARY KEY,
  recipe_id INTEGER NOT NULL REFERENCES recipes(id),
  matched_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  local_date DATE NOT NULL,
  user_id INTEGER NOT NULL REFERENCES users(id)
);

CREATE INDEX idx_user_id ON public.swipes(user_id);