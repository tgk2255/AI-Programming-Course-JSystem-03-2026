CREATE TABLE IF NOT EXISTS public.test_connection (
    id integer PRIMARY KEY,
    note text NOT NULL
);

INSERT INTO public.test_connection (id, note)
VALUES (1, 'postgres is ready')
ON CONFLICT (id) DO NOTHING;
