package models;

public interface IItem {
    // attach Observer
    void subscribe(Customer customer);

    // detach Observer
    void unsubscribe(Customer customer);

    // publish notifications to subscribers
    void notify_();
}
