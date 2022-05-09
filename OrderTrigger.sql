GO
CREATE TRIGGER OrderTrigger ON Orders
FOR INSERT
AS
BEGIN

SET IDENTITY_INSERT dbo.OrderTriggerLog ON

INSERT INTO dbo.OrderTriggerLog ([OrderDate], 
								[RequiredDate], [ShippedDate], [Freight], [ShipName], 
								[ShipAddress], [ShipCity], [ShipRegion], [ShipPostalCode], 
								[ShipCountry])
    SELECT
        OrderDate, RequiredDate, ShippedDate, 
		Freight, ShipName, ShipAddress, ShipCity, ShipRegion, ShipPostalCode, ShipCountry
        FROM inserted

SET IDENTITY_INSERT dbo.OrderTriggerLog OFF
END

GO
