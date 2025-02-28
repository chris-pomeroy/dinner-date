CREATE TABLE public.recipes (
  id serial NOT NULL PRIMARY KEY,
  title text NOT NULL,
  description text NOT NULL,
  image_name text NULL
);