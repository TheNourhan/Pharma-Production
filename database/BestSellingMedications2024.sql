CREATE VIEW BestSellingMedications2024 AS
SELECT 
    D.Governorate,
    M.TradeName AS MedicineName,
    SUM(PO.Quantity) AS TotalQuantity
FROM 
    PurchaseOrders PO
JOIN 
    Medicines M ON PO.MedicineID = M.MedicineID
JOIN 
    Distributors D ON PO.DistributorID = D.DistributorID
WHERE 
    EXTRACT(YEAR FROM PO.OrderDate) = 2024
GROUP BY 
    D.Governorate, M.TradeName
ORDER BY 
    D.Governorate ASC, TotalQuantity DESC;
