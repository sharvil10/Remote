public class Mypoint
{
	protected double x;
	protected double y;
	Mypoint(double x,double y)
	{
		this.x=x;
		this.y=y;
	}
	public void setPoint(double x,double y)
	{
		this.x=x;
		this.y=y;
	}
	public double getX()
	{
		return x;
	}
	public double getY()
	{
		return y;
	}
	public void obj()
	{
		System.out.println("This is point object");
	}
	public void print()
	{
		System.out.println("("+this.x+","+this.y+")");
	}
}