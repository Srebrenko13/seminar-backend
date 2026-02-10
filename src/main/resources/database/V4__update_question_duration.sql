UPDATE question
SET duration = CASE
    WHEN duration = 20 THEN 15
    WHEN duration = 15 THEN 10
    ELSE duration
END
WHERE duration in (15, 20);